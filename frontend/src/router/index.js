import { createRouter, createWebHistory } from "vue-router";
import Home from "@/components/Home.vue";
import CourseList from "@/components/CourseList.vue";
import SubjetsList from "@/components/SubjetsList.vue";
import SubjetsForm from "@/components/SubjetsForm.vue";
import CourseDetails from "@/components/CourseDetails.vue";
import CourseForm from "@/components/CourseForm.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
    },

    {
      path: "/course-list",
      name: "CourseList",
      component: CourseList,
    },
    {
      path: "/curso-form",
      name: "CourseForm",
      component: CourseForm,
    },
    {
      path: "/asignaturas",
      name: "asignaturas",
      component: SubjetsList,
    },
    {
      path: "/curso-form/:id",
      name: "EditCourseForm",
      component: CourseForm,
    },
    {
      path: "/curso/:id",
      name: "CourseDetails",
      component: CourseDetails,
      props: true,
    },
    {
      path: "/asignatura-form",
      name: "asignaturasForm",
      component: SubjetsForm,
    },
    {
      path: "/asignatura-form/:id",
      name: "asignaturasEditForm",
      component: SubjetsForm,
      props: true,
    },
  ],
});

export default router;
