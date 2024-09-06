<script>
import { ref, onMounted } from "vue";
import axios from "./axios";
import { RouterLink, RouterView } from "vue-router";

const cursos = ref([]);

const fetchCursos = async () => {
  try {
    const response = await axios.get("/api/cursos/");
    cursos.value = response.data;
  } catch (error) {
    console.error("Error fetching cursos:", error);
  }
};

onMounted(() => {
  fetchCursos();
});
</script>

<template>
  <header>
    <nav>
      <div class="divBandera">
        <img src="../public/bandera.png" alt="bandera" class="bandera" />
      </div>
      <div class="rutas">
        <RouterLink to="/" class="ruta">Inicio</RouterLink>
        <RouterLink to="/course-list" class="ruta">Cursos</RouterLink>
        <RouterLink to="/asignaturas" class="ruta">Asignaturas</RouterLink>
      </div>
      <div>
        <img src="../public/Logo2.png" alt="logo" class="logo" />
      </div>
    </nav>
  </header>

  <main class="main-content">
    <RouterView />
  </main>
</template>

<style scoped>
header {
  margin: 0;
  background-color: #3f9fd6;
  color: white;
  padding: 8px;
}

nav {
  display: flex;
  justify-content: space-between;
  gap: 50px;
  align-items: center;
  height: 80px;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background-color: #3f9fd6;
  z-index: 1000;
}

.main-content {
  margin-top: 70px;
}

.rutas {
  display: flex;
  gap: 24px;
}

.router-link-active {
  font-weight: bold;
  text-decoration: underline;
  font-size: 26px;
}

.ruta {
  color: black;
  text-decoration: none;
  font-size: 20px;
  padding: 5px;
}

.ruta:hover {
  font-size: 24px;
  color: blueviolet;
  text-decoration: underline;
}

.bandera {
  width: 165px;
  height: 80px;
  margin-left: 15px;
  padding: 5px;
}

.logo {
  display: flex;
  justify-content: flex-end;
  width: 130px;
  height: 65px;
  margin-right: 15px;
}

@media (max-width: 768px) {
  nav {
    flex-direction: column;
    gap: 20px;
    height: auto;
    padding: 10px;
    position: relative;
  }

  .main-content {
    margin-top: 100px;
  }

  .bandera, .logo {
    display: none;
  }
}
</style>
