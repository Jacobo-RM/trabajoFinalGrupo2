<template>
    <div>
      <button class="aniadir" @click="goToCreateAsignatura">Nueva</button>
  
      <table v-if="asignaturas.length">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Curso</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="asignatura in asignaturas" :key="asignatura.id">
            <td>{{ asignatura.nombre }}</td>
            <td>{{ asignatura.descripcion }}</td>
            <td>{{ asignatura.curso.nombre }}</td>
            <td>
              <button class="btndelete" @click="confirmDelete(asignatura.id)">Eliminar</button>
              <button @click="goToEditAsignatura(asignatura.id)">Editar</button>
              <button @click="showDetails(asignatura)">Ver Detalle</button>
            </td>
          </tr>
        </tbody>
      </table>
      <p v-else>No hay asignaturas disponibles.</p>
  
      <div v-if="modalVisible" class="modal-overlay">
      <div class="modal-content">
        <button class="close-btn" @click="modalVisible = false">×</button>
        <h2>Detalles</h2>
        <p><strong>Nombre:</strong> {{ selectedAsignatura.nombre }}</p>
        <p><strong>Descripción:</strong> {{ selectedAsignatura.descripcion }}</p>
        <p><strong>Curso:</strong> {{ selectedAsignatura.curso.nombre }}</p>
        <p><strong>Créditos:</strong> {{ selectedAsignatura.creditos }}</p>
        <p><strong>Número de Horas:</strong> {{ selectedAsignatura.num_horas }}</p>
        <p><strong>Tipo de Asignatura:</strong> {{ selectedAsignatura.tipo }}</p>
      </div>
    </div>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from 'vue';
  import axios from 'axios'; 
  import { useRouter } from 'vue-router';
  
  export default {
    setup() {
      const asignaturas = ref([]);
      const modalVisible = ref(false);
      const selectedAsignatura = ref(null);
      const router = useRouter();
  
      const fetchAsignaturas = async () => {
        try {
          const response = await axios.get('/api/cursos/asignaturas');
          asignaturas.value = response.data;
        } catch (error) {
          console.error("Error fetching asignaturas:", error);
        }
      };
  
      const deleteAsignatura = async (id) => {
        try {
          const confirmed = confirm('¿Estás seguro de que deseas eliminar esta asignatura?');
          if (confirmed) {
            await axios.delete(`/api/cursos/borrarAsignatura/${id}`);
            await fetchAsignaturas();
          }
        } catch (error) {
          console.error("Error deleting asignatura:", error);
        }
      };
  
      const confirmDelete = (id) => {
        deleteAsignatura(id);
      };
  
      const showDetails = (asignatura) => {
        selectedAsignatura.value = asignatura;
        modalVisible.value = true;
      };
  
      const goToCreateAsignatura = () => {
        router.push('/asignatura-form');
      };
  
      const goToEditAsignatura = (id) => {
        router.push(`/asignatura-form/${id}`);
      };
  
      onMounted(() => {
        fetchAsignaturas();
      });
  
      return {
        asignaturas,
        confirmDelete,
        modalVisible,
        selectedAsignatura,
        showDetails,
        goToCreateAsignatura,
        goToEditAsignatura
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
  .btndelete {
    background-color: #ff4d4d;
    color: white;
    border: none;
    padding: 5px 10px;
    cursor: pointer;
    border-radius: 5px;
  }
  .btndelete:hover {
    background-color: #ff1a1a;
  }
  .aniadir {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    border-radius: 4px;
    margin-right: 10px;
    width: 100%;
  }
  
  .modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .modal-content {
    background: white;
    padding: 20px;
    border-radius: 8px;
    width: 300px;
    text-align: center;
    position: relative;
  }
  
  .close-btn {
    position: absolute;
    top: 10px;
    right: 10px;
    background: #ff4d4d;
    border: none;
    color: white;
    font-size: 20px;
    cursor: pointer;
  }
  </style>
  
  