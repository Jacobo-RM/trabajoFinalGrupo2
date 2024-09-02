import { createRouter, createWebHistory } from "vue-router";
import Home from "@/components/Home.vue";
import CourseList from "@/components/CourseList.vue";

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

  ],
});

export default router;
