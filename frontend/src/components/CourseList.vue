<template>
  <div class="cursos">
    <h1>cursos</h1>
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Description</th>
          <th>Start Date</th>
          <th>End Date</th>
          <th>Price</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="curso in cursos" :key="curso.id">
          <td>{{ curso.nombre }}</td>
          <td>{{ curso.descripcion }}</td>
          <td>{{ curso.fechaInicio }}</td>
          <td>{{ curso.fechaFin }}</td>
          <td>{{ curso.precio.toFixed(2) }} €</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const cursos = ref([]);

const fetchcursos = async () => {
  try {
    const response = await axios.get('/api/cursos/');
    cursos.value = response.data;
  } catch (error) {
    console.error('Error fetching cursos:', error);
  }
};
onMounted(() => {
  fetchcursos();
});
</script>

<style scoped>
.cursos table {
  width: 100%;
  border-collapse: collapse;
}
.cursos th, .cursos td {
  border: 1px solid #ddd;
  padding: 8px;
}
.cursos th {
  background-color: #f4f4f4;
}
</style>
