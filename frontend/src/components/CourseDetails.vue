<template>
    <div>
      <div v-if="curso">
        <h2>Detalles del Curso</h2>
        <p><strong>Nombre:</strong> {{ curso.nombre }}</p>
        <p><strong>Descripción:</strong> {{ curso.descripcion }}</p>
        <p><strong>Fecha Inicio:</strong> {{ curso.fecha_inicio }}</p>
        <p><strong>Fecha Fin:</strong> {{ curso.fecha_fin }}</p>
        <p><strong>Precio:</strong> {{ curso.precio.toFixed(2) }} €</p>
        <p><strong>Año:</strong> {{ curso.anio }}</p>
  
        <h3>Asignaturas:</h3>
        <div class="addBtn">
          <button
            class="aniadir"
            @click="goToCreateAsignatura"
            title="Crear nueva asignatura"
          >
            <!-- Icono para añadir asignatura -->
          </button>
        </div>
        <div v-if="asignaturas.length" class="cards-container">
          <div v-for="asignatura in asignaturas" :key="asignatura.id" class="card">
            <img
              src="../../public/CICHD2.png"
              alt="Imagen de la asignatura"
              class="card-img-top"
            />
            <div class="card-body">
              <p class="card-title">{{ asignatura.nombre }}</p>
              <p class="card-text">{{ asignatura.descripcion }}</p>
              <div class="card-actions">
                <button
                  @click="goToEditAsignatura(asignatura.id)"
                  class="btn btn-primary"
                  title="Editar"
                >
                  <!-- Icono para editar asignatura -->
                </button>
                <button
                  class="btn btndelete"
                  @click="confirmDelete(asignatura.id)"
                  title="Eliminar"
                >
                  <!-- Icono para eliminar asignatura -->
                </button>
                <button
                  @click="showDetails(asignatura)"
                  class="btn btn-secondary"
                  title="Ver detalles"
                >
                  <!-- Icono para ver detalles de asignatura -->
                </button>
              </div>
            </div>
          </div>
        </div>
        <div v-else>No hay asignaturas disponibles.</div>
        <button @click="goBack">Volver</button>
      </div>
      <div v-else>
        <p>Cargando detalles del curso...</p>
      </div>
  
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
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { useRoute, useRouter } from 'vue-router';
  import axios from 'axios';
  
  const curso = ref(null);
  const asignaturas = ref([]);
  const modalVisible = ref(false);
  const selectedAsignatura = ref(null);
  const route = useRoute();
  const router = useRouter();
  
  const fetchCursoDetalles = async (id) => {
    try {
      const cursoResponse = await axios.get(`/api/cursos/${id}`);
      curso.value = cursoResponse.data;
  
      const asignaturasResponse = await axios.get(`/api/cursos/${id}/asignaturas`);
      asignaturas.value = asignaturasResponse.data;
    } catch (error) {
      console.error('Error fetching curso detalles o asignaturas:', error);
    }
  };
  
  const goBack = () => {
    router.push('/CourseList'); 
  };
  
  const goToCreateAsignatura = () => {
  router.push({ path: `/asignatura-form`, query: { cursoEditable: false, cursoId: curso.value.id } });
};


const goToEditAsignatura = (id) => {
  router.push({ path: `/asignatura-form/${id}`, query: { cursoEditable: false } });
};
  
  const confirmDelete = async (id) => {
    const confirmed = confirm('¿Estás seguro de que deseas eliminar esta asignatura?');
    if (confirmed) {
      try {
        await axios.delete(`/api/cursos/borrarAsignatura/${id}`);
        await fetchCursoDetalles(curso.value.id); // Recarga las asignaturas
      } catch (error) {
        console.error('Error deleting asignatura:', error);
      }
    }
  };
  
  const showDetails = (asignatura) => {
    selectedAsignatura.value = asignatura;
    modalVisible.value = true;
  };
  
  onMounted(() => {
    const id = route.params.id;
    fetchCursoDetalles(id);
  });
  </script>
  
  <style scoped>
  h2 {
    margin-bottom: 10px;
  }
  
  p {
    margin: 5px 0;
  }
  
  ul {
    list-style-type: none;
    padding: 0;
  }
  
  li {
    margin: 5px 0;
  }
  
  button {
    margin-top: 20px;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    background-color: #ddd;
    cursor: pointer;
  }
  
  .addBtn {
    display: flex;
    justify-content: flex-end;
    padding: 18px;
  }
  
  .aniadir:hover {
    background-color: #1e1a1a;
  }
  .aniadir {
    background-color: #4caf50;
    display: flex;
    align-items: center;
    color: white;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    margin-right: 5px;
    border-radius: 50px;
    filter: drop-shadow(0 0 0.5rem #000000);
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
  
  .cards-container {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    justify-content: center;
    text-align: center;
  }
  
  .card {
    border: 1px solid #ddd;
    border-radius: 8px;
    width: 300px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    display: flex;
    flex-direction: column;
  }
  
  .card-img-top {
    width: 100%;
    height: 150px;
    object-fit: cover;
  }
  
  .card-body {
    padding-top: 8px;
    padding-bottom: 2px;
    display: flex;
    flex-direction: column;
    align-items: center;
    flex-grow: 1;
  }
  
  .card-title {
    font-size: 1.25em;
  }
  
  .card-text {
    flex-grow: 1;
    padding-bottom: 4px;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
  }
  
  .card-actions {
    display: flex;
    justify-content: space-between;
    margin-bottom: 8px;
    gap: 20px;
  }
  
  .btn {
    padding: 8px;
  }
  
  .btn:hover {
    background-color: #1e1a1a;
    color: white;
  }
  
  .btn-primary {
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  .btn-secondary {
    background-color: #29d21d;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
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
  </style>
  