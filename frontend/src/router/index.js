import { createRouter, createWebHistory } from "vue-router";
import Home from "@/components/Home.vue";
import CourseList from "@/components/CourseList.vue";
import SubjetsList from "@/components/SubjetsList.vue";
import CoueseForm from "@/components/CourseForm.vue";
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
      path: "/CourseList",
      name: "CourseList",
      component: CourseList,
    },
    {
      path: "/CourseForm",
      name: "CourseForm",
      component: CoueseForm,
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
