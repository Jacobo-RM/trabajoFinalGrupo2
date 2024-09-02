<template>
  <div id="app">
    <h1>Lista de Cursos</h1>
    <ul>
      <li v-for="curso in cursos" :key="curso.id">{{ curso.nombre }}</li>
    </ul>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import axios from './axios'; // Importa tu instancia de Axios

export default {
  setup() {
    const cursos = ref([]);

    const fetchCursos = async () => {
      try {
        const response = await axios.get('/api/cursos/'); // Corregido para coincidir con el endpoint del backend
        cursos.value = response.data;
      } catch (error) {
        console.error('Error fetching cursos:', error);
      }
    };

    onMounted(() => {
      fetchCursos();
    });

    return { cursos };
  }
};
</script>

<style scoped>
/* Agrega tus estilos aquí */
</style>
