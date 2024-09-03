import { createRouter, createWebHistory } from "vue-router";
import Home from "@/components/Home.vue";
import SubjetsList from "@/components/SubjetsList.vue";
import SubjetsForm from "@/components/SubjetsForm.vue";

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
    {
      path: '/asignatura-form',
      name: 'asignaturasForm',
      component: SubjetsForm,
    },
    {
      path: '/asignatura-form/:id',
      name: 'asignaturasEditForm',
      component: SubjetsForm,
      props: true 
    },

  ],
});

export default router;
