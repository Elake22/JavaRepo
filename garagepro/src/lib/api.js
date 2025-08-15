// src/lib/api.js
const API_BASE = (import.meta.env.VITE_API_BASE ?? "http://localhost:8080").replace(/\/$/, "");

/**
 * Minimal fetch wrapper with good error messages.
 */
async function request(path, { headers, ...opts } = {}) {
  const res = await fetch(`${API_BASE}${path}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      ...headers,
    },
    ...opts,
  });

  const ct = res.headers.get("content-type") || "";

  // Error handling (parse JSON error if available)
  if (!res.ok) {
    let msg = `HTTP ${res.status}`;
    try {
      if (ct.includes("application/json")) {
        const j = await res.json();
        msg = j?.message || j?.error || msg;
      } else {
        const text = await res.text();
        msg = text || msg;
      }
    } catch {
      // ignore parse errors
    }
    throw new Error(msg);
  }

  // 204 No Content
  if (res.status === 204) return null;

  // Success body
  return ct.includes("application/json") ? res.json() : null;
}

/* -------- API helpers -------- */
export const getServices    = () => request("/api/services");
export const getMechanics   = () => request("/api/mechanics");
export const createCustomer = (body) =>
  request("/api/customers", { method: "POST", body: JSON.stringify(body) });
export const createBooking  = (body) =>
  request("/api/bookings",  { method: "POST", body: JSON.stringify(body) });
export const getBooking     = (id) =>
  request(`/api/bookings/${id}`);

/** Optional filters: { status, q } */
export function listBookings(params = {}) {
  const qs = new URLSearchParams();
  if (params.status) qs.set("status", params.status);
  if (params.q)      qs.set("q", params.q);
  const suffix = qs.toString() ? `?${qs}` : "";
  return request(`/api/bookings${suffix}`);
}

export const updateBooking = (id, body) =>
  request(`/api/bookings/${id}`, { method: "PUT", body: JSON.stringify(body) });

export const deleteBooking = (id) =>
  request(`/api/bookings/${id}`, { method: "DELETE" });
