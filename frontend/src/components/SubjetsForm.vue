<template>
  <div class="addForm">
    <form @submit.prevent="handleSubmit">
      <div>
        <label for="nombre">Nombre:</label>
        <input
          type="text"
          id="nombre"
          v-model="newAsignatura.nombre"
          required
        />
      </div>
      <div>
        <label for="descripcion">Descripción:</label>
        <textarea
          id="descripcion"
          v-model="newAsignatura.descripcion"
          required
        ></textarea>
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
      <div class="button-group">
        <button type="submit">{{ isEditMode ? "Actualizar" : "Crear" }}</button>
        <button type="button" @click="goToAsignaturas">Volver</button>
      </div>
    </form>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useRoute, useRouter } from "vue-router";

export default {
  setup() {
    const newAsignatura = ref({
      id: null,
      nombre: "",
      descripcion: "",
      cursoId: "",
    });

    const cursos = ref([]);
    const isEditMode = ref(false);
    const route = useRoute();
    const router = useRouter();

    const fetchCursos = async () => {
      try {
        const response = await axios.get("/api/cursos/");
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
          cursoId: response.data.curso.id,
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
            curso: { id: newAsignatura.value.cursoId },
          });
          alert("Asignatura actualizada con éxito");
        } else {
          await axios.post("/api/cursos/agregarAsignatura", {
            nombre: newAsignatura.value.nombre,
            descripcion: newAsignatura.value.descripcion,
            curso: { id: newAsignatura.value.cursoId },
          });
          alert("Asignatura creada con éxito");
        }
        router.push("/asignaturas");
      } catch (error) {
        console.error("Error saving asignatura:", error);
      }
    };

    const goToAsignaturas = () => {
      router.push("/asignaturas");
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
      isEditMode,
    };
  },
};
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
