const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [{ path: "", component: () => import("pages/IndexPage.vue") }],
  },
  {
    path: "/funciones/:func",
    name: "funciones",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        name: "funciones",
        path: "",
        component: () => import("pages/Funciones.vue"),
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
