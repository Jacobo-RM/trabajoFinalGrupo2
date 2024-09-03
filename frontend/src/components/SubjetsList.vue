<template>
    <div>
      <table v-if="asignaturas.length">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Curso</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="asignatura in asignaturas" :key="asignatura.id">
            <td>{{ asignatura.nombre }}</td>
            <td>{{ asignatura.descripcion }}</td>
            <td>{{ asignatura.curso.nombre }}</td>
          </tr>
        </tbody>
      </table>
      <p v-else>No hay asignaturas disponibles.</p>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  
  export default {
    setup() {
      const asignaturas = ref([]);
  
      const fetchAsignaturas = async () => {
        try {
          const response = await axios.get('/api/cursos/asignaturas'); // Ruta sin barra al final
          asignaturas.value = response.data;
        } catch (error) {
          console.error("Error fetching asignaturas:", error);
        }
      };
  
      onMounted(() => {
        fetchAsignaturas();
      });
  
      return {
        asignaturas
      };
    }
  };
  </script>
  
  <style scoped>
  table {
    width: 100%;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #ddd;
    padding: 8px;
  }
  th {
    background-color: #f2f2f2;
  }
  </style>
  