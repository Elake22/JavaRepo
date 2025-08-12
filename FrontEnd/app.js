// When serving from VS Code Live Server, hit your backend directly:
const API = 'http://localhost:8080';

const $ = (s) => document.querySelector(s);
const setList = (sel, items) => $(sel).innerHTML = items.map(li => `<li>${li}</li>`).join('');

async function api(path, opt = {}) {
  const res = await fetch(API + path, { headers: { 'Content-Type': 'application/json' }, ...opt });
  if (!res.ok) throw new Error(`${res.status} ${await res.text()}`);
  return res.headers.get('content-type')?.includes('json') ? res.json() : null;
}

function fillSelect(sel, items, label) {
  $(sel).innerHTML = items.map(x => `<option value="${x.id}">${label(x)}</option>`).join('');
}

// ---------------- Services ----------------
let servicesCache = [];

async function loadServices() {
  const data = await api('/api/services');
  servicesCache = data;

  // list on the page
  setList('#servicesList', data.map(s => `#${s.id} ${s.name} — $${Number(s.price).toFixed(2)}`));

  // select (value MUST be the numeric id)
  fillSelect('#svcSelect', data, s => s.name);

  // default price if empty
  const svcSel = $('#svcSelect');
  if (data.length && !$('#quote').value) {
    const first = data.find(s => s.id === Number(svcSel.value));
    if (first) $('#quote').value = Number(first.price).toFixed(2);
  }
}

// handle add service form
$('#serviceForm').addEventListener('submit', async (e) => {
  e.preventDefault();
  const f = e.target;
  await api('/api/services', {
    method: 'POST',
    body: JSON.stringify({
      name: f.name.value.trim(),
      description: f.description.value.trim(),
      price: Number(f.price.value)
    })
  });
  f.reset();
  loadServices();
});

// auto-fill price when service changes
$('#svcSelect').addEventListener('change', (e) => {
  const svc = servicesCache.find(s => s.id === Number(e.target.value));
  if (svc) $('#quote').value = Number(svc.price).toFixed(2);
});

// ---------------- Customers & Mechanics ----------------
async function loadCustomers() {
  const d = await api('/api/customers');
  fillSelect('#custSelect', d, c => `${c.firstName} ${c.lastName}`);
}

function labelMechanic(m) {
  return m.specialty ? `${m.name} (${m.specialty})` : m.name;
}

async function loadMechanics() {
  const d = await api('/api/mechanics');
  fillSelect('#mechSelect', d, labelMechanic);
}

// ---------------- Bookings ----------------
async function loadBookings() {
  try {
    const data = await api('/api/bookings');
    if (!data.length) {
      $('#bookingsList').innerHTML = '<li>No bookings yet</li>';
      return;
    }
    $('#bookingsList').innerHTML = data.map(b =>
      `<li>#${b.id} — ${b.customer?.firstName ?? ''} ${b.customer?.lastName ?? ''} · ${b.service?.name ?? ''} · ${new Date(b.appointmentAt).toLocaleString()} · $${Number(b.quotedPrice).toFixed(2)} · ${b.status}</li>`
    ).join('');
  } catch (e) {
    console.error(e);
    $('#bookingsList').innerHTML = '<li>Failed to load bookings</li>';
  }
}

$('#bookBtn').addEventListener('click', async () => {
  try {
    // datetime-local gives "YYYY-MM-DDTHH:mm" → add ":00" for seconds if needed
    const v = $('#when').value;
    const appt = v ? (v.length === 16 ? v + ':00' : v)
                   : new Date(Date.now() + 3600000).toISOString().slice(0, 19);

    const customerId = Number($('#custSelect').value);
    const serviceId  = Number($('#svcSelect').value);
    const mechanicId = Number($('#mechSelect').value);

    if (!customerId || !serviceId || !mechanicId || Number.isNaN(customerId) || Number.isNaN(serviceId) || Number.isNaN(mechanicId)) {
      $('#msg').textContent = 'Pick a customer, service, and mechanic before booking.';
      $('#msg').style.color = '#c1272d';
      return;
    }

    const price = Number($('#quote').value);
    if (!Number.isFinite(price)) {
      $('#msg').textContent = 'Enter a valid price.';
      $('#msg').style.color = '#c1272d';
      return;
    }

    const payload = {
      customer:  { id: customerId },
      service:   { id: serviceId },
      mechanic:  { id: mechanicId },
      appointmentAt: appt,
      quotedPrice: price,
      status: $('#status').value,
      notes: $('#notes').value.trim()
    };

    // Debug if needed:
    // console.log('POST /api/bookings', payload);

    await api('/api/bookings', { method: 'POST', body: JSON.stringify(payload) });

    $('#msg').textContent = 'Booking created.';
    $('#msg').style.color = '#0a7a28';
    loadBookings();
  } catch (e) {
    $('#msg').textContent = e.message;
    $('#msg').style.color = '#c1272d';
  }
});

// ---------------- init ----------------
(async () => {
  // set default time = +1h
  const d = new Date(Date.now() + 3600000);
  $('#when').value = d.toISOString().slice(0, 16);

  await Promise.all([loadServices(), loadCustomers(), loadMechanics()]);
  await loadBookings();
})();
