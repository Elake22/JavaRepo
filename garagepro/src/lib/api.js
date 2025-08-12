// src/lib/api.js
const API = import.meta.env.VITE_API_BASE ?? 'http://localhost:8080';

async function request(path, opts = {}) {
  const res = await fetch(API + path, {
    headers: { 'Content-Type': 'application/json' },
    ...opts,
  });
  if (!res.ok) throw new Error(`${res.status} ${await res.text()}`);
  return res.headers.get('content-type')?.includes('json') ? res.json() : null;
}

export const getServices  = () => request("/api/services");
export const getMechanics = () => request("/api/mechanics");
export const createCustomer = (body) => request("/api/customers", { method: "POST", body: JSON.stringify(body) });
export const createBooking  = (body) => request("/api/bookings",  { method: "POST", body: JSON.stringify(body) });
export const getBooking     = (id)   => request(`/api/bookings/${id}`);