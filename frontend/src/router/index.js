import { createRouter, createWebHistory } from "vue-router";
import Home from "@/components/Home.vue";
import SubjetsList from "@/components/SubjetsList.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
    },
    {
      path: '/asignaturas',
      name: 'asignaturas',
      component: SubjetsList,
    },
  ],
});

export default router;
