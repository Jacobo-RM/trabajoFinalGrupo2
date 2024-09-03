<template>
    <div>
      <form @submit.prevent="handleSubmit">
        <div>
          <label for="nombre">Nombre:</label>
          <input type="text" id="nombre" v-model="newAsignatura.nombre" required />
        </div>
        <div>
          <label for="descripcion">Descripción:</label>
          <textarea id="descripcion" v-model="newAsignatura.descripcion" required></textarea>
        </div>
        <div>
          <label for="curso">Curso:</label>
          <select id="curso" v-model="newAsignatura.cursoId" required>
            <option value="" disabled>Seleccione un curso</option>
            <option v-for="curso in cursos" :key="curso.id" :value="curso.id">
              {{ curso.nombre }}
            </option>
          </select>
        </div>
        <button type="submit">{{ isEditMode ? 'Actualizar' : 'Crear' }}</button>
        <button type="button" @click="goToAsignaturas">Volver</button>
      </form>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  import { useRoute, useRouter } from 'vue-router';
  
  export default {
    setup() {
      const newAsignatura = ref({
        id: null,
        nombre: '',
        descripcion: '',
        cursoId: ''
      });
  
      const cursos = ref([]);
      const isEditMode = ref(false);
      const route = useRoute();
      const router = useRouter();
  
      const fetchCursos = async () => {
        try {
          const response = await axios.get('/api/cursos/');
          cursos.value = response.data;
        } catch (error) {
          console.error("Error fetching cursos:", error);
        }
      };
  
      const fetchAsignatura = async (id) => {
        try {
          const response = await axios.get(`/api/cursos/asignaturas/${id}`);
          newAsignatura.value = {
            id: response.data.id,
            nombre: response.data.nombre,
            descripcion: response.data.descripcion,
            cursoId: response.data.curso.id
          };
        } catch (error) {
          console.error("Error fetching asignatura:", error);
        }
      };
  
      const handleSubmit = async () => {
        try {
          if (isEditMode.value) {
            await axios.put(`/api/cursos/asignaturas/${newAsignatura.value.id}`, {
              nombre: newAsignatura.value.nombre,
              descripcion: newAsignatura.value.descripcion,
              curso: { id: newAsignatura.value.cursoId }
            });
            alert('Asignatura actualizada con éxito');
          } else {
            await axios.post('/api/cursos/agregarAsignatura', {
              nombre: newAsignatura.value.nombre,
              descripcion: newAsignatura.value.descripcion,
              curso: { id: newAsignatura.value.cursoId }
            });
            alert('Asignatura creada con éxito');
          }
          router.push('/asignaturas');
        } catch (error) {
          console.error("Error saving asignatura:", error);
        }
      };
  
      const goToAsignaturas = () => {
        router.push('/asignaturas');
      };
  
      onMounted(() => {
        fetchCursos();
        const id = route.params.id;
        if (id) {
          isEditMode.value = true;
          fetchAsignatura(id);
        }
      });
  
      return {
        newAsignatura,
        cursos,
        handleSubmit,
        goToAsignaturas,
        isEditMode
      };
    }
  };
  </script>
  
  <style scoped>
  form {
    max-width: 500px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
  }
  
  div {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
  }
  
  input, textarea, select {
    width: 100%;
    padding: 8px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }
  
  button {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    border-radius: 4px;
    margin-right: 10px;
  }
  
  button:hover {
    background-color: #45a049;
  }
  </style>
  