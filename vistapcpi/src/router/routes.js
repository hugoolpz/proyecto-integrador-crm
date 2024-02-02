const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [{ path: "", component: () => import("pages/IndexPage.vue") }],
  },
  {
    path: "/proyectos",
    component: () => import("layouts/Proyectos.vue"),
    children: [{ path: "", component: () => import("pages/Proyectos.vue") }],
  },
  {
    path: "/facturas",
    component: () => import("layouts/Factura.vue"),
    children: [{ path: "", component: () => import("pages/Factura.vue") }],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
