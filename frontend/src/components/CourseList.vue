<template>
  <div>
    <button @click="goToAddCurso">Agregar Curso</button>
    <table>
      <thead>
        <tr>
          <th>Nombre</th>
          <th>Descripción</th>
          <th>Fecha Inicio</th>
          <th>Fecha Fin</th>
          <th>Precio</th>
          <th>Año</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="curso in cursos" :key="curso.id">
          <td>{{ curso.nombre }}</td>
          <td>{{ curso.descripcion }}</td>
          <td>{{ curso.fecha_inicio }}</td>
          <td>{{ curso.fecha_fin }}</td>
          <td>{{ curso.precio.toFixed(2) }} €</td>
          <td>{{ curso.anio }}</td>
          <td>
            <button @click="showModal(curso.id)">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>

    <div v-if="modalVisible" class="modal-overlay">
      <div class="modal">
        <p>¿Estás seguro de que deseas eliminar este curso?</p>
        <button @click="deleteCurso(cursoIdBorrar)">Sí, eliminar</button>
        <button @click="closeModal">Cancelar</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const cursos = ref([]);
const modalVisible = ref(false);
const cursoIdBorrar = ref(null);
const router = useRouter();

const fetchCursos = async () => {
  try {
    const response = await axios.get("/api/cursos/");
    cursos.value = response.data;
  } catch (error) {
    console.error("Error fetching cursos:", error);
  }
};

const deleteCurso = async (id) => {
  try {
    const confirmed = confirm(
      "¿Estás seguro de que deseas eliminar este curso?"
    );
    if (confirmed) {
      await axios.delete(`/api/cursos/borrarCurso/${id}`);
      cursos.value = cursos.value.filter((curso) => curso.id !== id);
      closeModal();
    }
  } catch (error) {
    console.error("Error deleting curso:", error);
  }
};

const showModal = (id) => {
  cursoIdBorrar.value = id;
  modalVisible.value = true;
};

const closeModal = () => {
  modalVisible.value = false;
  cursoIdBorrar.value = null;
};

const goToEditCurso = (id) => {
  router.push(`/curso-form/${id}`);
};

const goToAddCurso = () => {
  router.push("/curso-form");
};

onMounted(() => {
  fetchCursos();
});
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}
th,
td {
  border: 1px solid #ddd;
  padding: 8px;
}
th {
  background-color: #f4f4f4;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  width: 300px;
  text-align: center;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.modal button {
  margin: 5px;
  padding: 10px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.modal button:first-of-type {
  background-color: #ff5f5f;
  color: white;
}

.modal button:last-of-type {
  background-color: #ddd;
}
</style>
