<template>
  <div class="addForm">
    <form @submit.prevent="submitForm">
      <div>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" v-model="curso.nombre" required />
      </div>
      <div>
        <label for="descripcion">Descripción:</label>
        <textarea
          id="descripcion"
          v-model="curso.descripcion"
          required
        ></textarea>
      </div>
      <div>
        <label for="fecha_inicio">Fecha Inicio:</label>
        <input
          type="date"
          id="fechaInicio"
          v-model="curso.fecha_inicio"
          required
        />
      </div>
      <div>
        <label for="fecha_fin">Fecha Fin:</label>
        <input type="date" id="fechaFin" v-model="curso.fecha_fin" required />
      </div>
      <div>
        <label for="precio">Precio:</label>
        <input type="number" id="precio" v-model="curso.precio" required />
      </div>
      <div>
        <label for="anio">Año:</label>
        <input type="number" id="anio" v-model="curso.anio" required />
      </div>
      <div class="button-group">
        <button type="submit">
          {{ isEditMode ? "Actualizar" : "Guardar" }}
        </button>
        <button type="button" @click="goToCursos">Volver</button>
      </div>
    </form>
  </div>
</template>
<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useRoute, useRouter } from "vue-router";
import { useToast } from "vue-toast-notification";

const curso = ref({
  nombre: "",
  descripcion: "",
  fecha_inicio: "",
  fecha_fin: "",
  precio: 0,
  anio: "",
});

const route = useRoute();
const isEditMode = ref(false);
const router = useRouter();
const toast = useToast();

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
      toast.success("Curso actualizado exitosamente");
    } else {
      await axios.post('/api/cursos/agregarCurso', curso.value);
      toast.success("Curso agregado exitosamente");
    }
    resetForm();
  } catch (error) {
    console.error('Error saving curso:', error);
  }
};


const goToCursos = () => {
  router.push("/course-list");
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
.addForm {
  display: flex;
  justify-content: center;
  align-items: center;
  height: calc(100vh - 100px);
  background-color: #f9f9f9;
}

form {
  display: flex;
  flex-direction: column;
  max-width: 600px;
  padding: 40px;
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  gap: 20px;
}

form div {
  display: flex;
  flex-direction: column;
  width: 100%;
  gap: 10px;
}

label {
  font-weight: bold;
  color: #333;
}

input,
textarea,
select {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 1em;
  background-color: #f9f9f9;
  transition: border-color 0.3s;
}

input:focus,
textarea:focus,
select:focus {
  border-color: #4caf50;
  outline: none;
}

.button-group {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 5px;
  font-size: 1em;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #45a049;
}

button[type="button"] {
  background-color: #f44336;
}

button[type="button"]:hover {
  background-color: #e53935;
}
</style>
