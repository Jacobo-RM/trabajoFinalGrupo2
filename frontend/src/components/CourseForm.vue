<template>
  <div>
    <h3>Añadir Curso</h3>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="nombre">Nombre</label>
        <input type="text" id="nombre" v-model="curso.nombre" required>
      </div>
      <div class="form-group">
        <label for="descripcion">Descripción</label>
        <input type="text" id="descripcion" v-model="curso.descripcion" required>
      </div>
      <div class="form-group">
        <label for="fecha_inicio">Fecha Inicio</label>
        <input type="date" id="fechaInicio" v-model="curso.fecha_inicio" required>
      </div>
      <div class="form-group">
        <label for="fecha_fin">Fecha Fin</label>
        <input type="date" id="fechaFin" v-model="curso.fecha_fin" required>
      </div>
      <div class="form-group">
        <label for="precio">Precio</label>
        <input type="number" id="precio" v-model="curso.precio" required>
      </div>
      <div class="form-group">
        <label for="anio">Año</label>
        <input type="number" id="anio" v-model="curso.anio" required>
      </div>
      <button type="submit">Guardar</button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';

const curso = ref({
  nombre: '',
  descripcion: '',
  fecha_inicio: '',
  fecha_fin: '',
  precio: 0,
  anio: ''
});

const route = useRoute();
const isEditMode = ref(false);

const fetchCurso = async (id) => {
  try {
    const response = await axios.get(`/api/cursos/${id}`);
    curso.value = response.data;
  } catch (error) {
    console.error('Error fetching curso:', error);
  }
};

const submitForm = async () => {
  try {
    if (isEditMode.value) {
      await axios.put(`/api/cursos/${curso.value.id}`, curso.value);
      alert('Curso actualizado exitosamente');
    } else {
      await axios.post('/api/cursos/agregarCurso', curso.value);
      alert('Curso agregado exitosamente');
    }
    resetForm();
  } catch (error) {
    console.error('Error saving curso:', error);
  }
};

const resetForm = () => {
  curso.value = {
    nombre: '',
    descripcion: '',
    fecha_inicio: '',
    fecha_fin: '',
    precio: 0,
    anio: ''
  };
};

onMounted(() => {
  const cursoId = route.query.cursoId;
  if (cursoId) {
    isEditMode.value = true;
    fetchCurso(cursoId);
  }
});
</script>


<style scoped>
.form-group {
  margin-bottom: 15px;
}
input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}
button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
}
</style>
