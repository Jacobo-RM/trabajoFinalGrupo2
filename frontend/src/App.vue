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
        <RouterLink to="/asignaturas" class="ruta">Asignaturas</RouterLink>
      </div>
      <div>
        <img src="../public/Logo2.png" alt="logo" class="logo" />
      </div>
    </nav>
  </header>

  <RouterView />
</template>

<style scoped>
header {
  margin: 0;
  background-color: #3f9fd6;
  color: white;
  padding: 10px;
}

nav {
  display: flex;
  justify-content: space-between;
  gap: 50px;
  align-items: center;
  height: 80px;
}

.rutas {
  display: flex;
  gap: 24px;
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
  width: 80px;
  height: 50px;
  border-radius: 1000px;
  filter: drop-shadow(0 0 0.5rem #000000);
  margin-left: 15px;
}

.logo {
  display: flex;
  justify-content: flex-end;
  width: 130px;
  height: 65px;
  margin-right: 15px;
}
</style>
