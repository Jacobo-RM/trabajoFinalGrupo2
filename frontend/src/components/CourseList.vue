<template>
  <div>
    <div class="bodySuperior">
      <div class="buscadorYfiltros">
        <div class="buscador">
          <input
            type="text"
            name="search"
            placeholder="Buscar curso"
            class="input"
            v-model="search"
          />
        </div>
        <label class="burger" for="burger">
          <input type="checkbox" id="burger" />
          <span></span>
          <span></span>
          <span></span>
          <div class="dropdown-content">
            <a href="#" @click="applyFilter('alfabeticamente')"
              >Ordenar Alfabéticamente</a
            >
            <a href="#" @click="applyFilter('reset')">Resetear Filtros</a>
          </div>
        </label>
      </div>
      <div class="addBtn">
        <button class="aniadir" @click="goToAddCurso" title="Agregar Curso">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            width="50"
            height="24"
            color="#FFFFFF"
            fill="none"
          >
            <path
              d="M12.5 22H9.5C6.20017 22 4.55025 22 3.52513 20.9209C2.5 19.8418 2.5 18.1051 2.5 14.6316V9.36842C2.5 5.89491 2.5 4.15816 3.52513 3.07908C4.55025 2 6.20017 2 9.5 2H12.5C15.7998 2 17.4497 2 18.4749 3.07908C19.5 4.15816 19.5 5.89491 19.5 9.36842V11"
              stroke="currentColor"
              stroke-width="1.5"
              stroke-linecap="round"
              stroke-linejoin="round"
            />
            <path
              d="M18 15L18 22M21.5 18.5L14.5 18.5"
              stroke="currentColor"
              stroke-width="1.5"
              stroke-linecap="round"
            />
            <path
              d="M7 2L7.0822 2.4932C7.28174 3.69044 7.38151 4.28906 7.80113 4.64453C8.22075 5 8.82762 5 10.0414 5H11.9586C13.1724 5 13.7793 5 14.1989 4.64453C14.6185 4.28906 14.7183 3.69044 14.9178 2.4932L15 2"
              stroke="currentColor"
              stroke-width="1.5"
              stroke-linecap="round"
              stroke-linejoin="round"
            />
            <path
              d="M7 16H11M7 11H15"
              stroke="currentColor"
              stroke-width="1.5"
              stroke-linecap="round"
            />
          </svg>
        </button>
      </div>
    </div>

    <div v-if="filteredCursos.length" class="cards-container">
      <div v-for="curso in filteredCursos" :key="curso.id" class="card">
        <img
          src="../../public/cursosLogo.png"
          alt="Imagen del curso"
          class="card-img-top"
        />
        <div class="card-body">
          <p class="card-title">{{ curso.nombre }}</p>
          <p class="card-text">{{ curso.descripcion }}</p>
          <div class="card-actions">
            <button
              @click="goToEditCurso(curso.id)"
              class="btn btn-primary"
              title="Editar"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
                width="24"
                height="24"
                color="#FFFFFF"
                fill="none"
              >
                <path
                  d="M7.01428 16.0029H11.0143M7.01428 11.0053H15.0143"
                  stroke="currentColor"
                  stroke-width="1.5"
                  stroke-linecap="round"
                />
                <path
                  d="M10.0143 21.9999H11.0143"
                  stroke="currentColor"
                  stroke-width="1.5"
                  stroke-linecap="round"
                />
                <path
                  d="M7.51083 21.9999C6.33921 21.9749 5.53921 21.9 4.76421 21.6751C3.71491 21.4002 2.94905 20.4757 2.72421 19.1763C2.43921 17.9519 2.53921 14.9284 2.51416 11.3302C2.51623 8.53874 2.3892 5.85787 2.8392 4.33362C3.1142 3.83386 3.3392 2.00976 7.06421 2.03475C7.68921 2.00976 14.3142 1.95978 15.3642 2.05973C18.8642 2.1347 19.2142 3.93381 19.4142 5.63298C19.541 6.88236 19.5142 8.88138 19.5142 11.0053M7.01421 2.00976C7.31421 3.63396 7.28921 4.68344 8.38921 4.93332C8.86421 5.00828 9.9495 5.00628 11.1142 5.00828C12.1538 5.01007 13.2142 5.01945 13.6892 4.90833C14.8642 4.63347 14.7392 3.18418 15.0392 2.00976"
                  stroke="currentColor"
                  stroke-width="1.5"
                  stroke-linecap="round"
                />
                <path
                  d="M18.2786 14.3787C16.9045 15.778 14.2563 18.2768 14.2563 18.4517C14.0427 18.7483 13.8565 19.3512 13.7316 20.2008C13.5747 20.9884 13.3868 21.6751 13.6067 21.875C13.8266 22.0749 14.6545 21.9064 15.5304 21.7251C16.2299 21.6501 16.8795 21.4002 17.2043 21.1504C17.679 20.7304 20.9018 17.4772 21.2766 17.0524C21.5514 16.6776 21.5764 15.9779 21.3365 15.5531C21.2016 15.2533 20.3522 14.4536 20.0774 14.2288C19.5777 13.9289 18.8782 13.8789 18.2786 14.3787Z"
                  stroke="currentColor"
                  stroke-width="1.5"
                  stroke-linecap="round"
                />
              </svg>
            </button>
            <button
              class="btn btndelete"
              @click="showModal(curso.id)"
              title="Eliminar"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
                width="24"
                height="24"
                color="#FFFFFF"
                fill="none"
              >
                <path
                  d="M19.5 5.5L18.8803 15.5251C18.7219 18.0864 18.6428 19.3671 18.0008 20.2879C17.6833 20.7431 17.2747 21.1273 16.8007 21.416C15.8421 22 14.559 22 11.9927 22C9.42312 22 8.1383 22 7.17905 21.4149C6.7048 21.1257 6.296 20.7408 5.97868 20.2848C5.33688 19.3626 5.25945 18.0801 5.10461 15.5152L4.5 5.5"
                  stroke="currentColor"
                  stroke-width="1.5"
                  stroke-linecap="round"
                />
                <path
                  d="M3 5.5H21M16.0557 5.5L15.3731 4.09173C14.9196 3.15626 14.6928 2.68852 14.3017 2.39681C14.215 2.3321 14.1231 2.27454 14.027 2.2247C13.5939 2 13.0741 2 12.0345 2C10.9688 2 10.436 2 9.99568 2.23412C9.8981 2.28601 9.80498 2.3459 9.71729 2.41317C9.32164 2.7167 9.10063 3.20155 8.65861 4.17126L8.05292 5.5"
                  stroke="currentColor"
                  stroke-width="1.5"
                  stroke-linecap="round"
                />
                <path
                  d="M9.5 16.5L9.5 10.5"
                  stroke="currentColor"
                  stroke-width="1.5"
                  stroke-linecap="round"
                />
                <path
                  d="M14.5 16.5L14.5 10.5"
                  stroke="currentColor"
                  stroke-width="1.5"
                  stroke-linecap="round"
                />
              </svg>
            </button>
            <router-link
              :to="{ name: 'CourseDetails', params: { id: curso.id } }"
            >
              <button class="btn btn-secondary" title="Ver detalles">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 24 24"
                  width="24"
                  height="24"
                  color="#FFFFFF"
                  fill="none"
                >
                  <path
                    d="M21.544 11.045C21.848 11.4713 22 11.6845 22 12C22 12.3155 21.848 12.5287 21.544 12.955C20.1779 14.8706 16.6892 19 12 19C7.31078 19 3.8221 14.8706 2.45604 12.955C2.15201 12.5287 2 12.3155 2 12C2 11.6845 2.15201 11.4713 2.45604 11.045C3.8221 9.12944 7.31078 5 12 5C16.6892 5 20.1779 9.12944 21.544 11.045Z"
                    stroke="currentColor"
                    stroke-width="1.5"
                  />
                  <path
                    d="M15 12C15 10.3431 13.6569 9 12 9C10.3431 9 9 10.3431 9 12C9 13.6569 10.3431 15 12 15C13.6569 15 15 13.6569 15 12Z"
                    stroke="currentColor"
                    stroke-width="1.5"
                  />
                </svg>
              </button>
            </router-link>
          </div>
        </div>
      </div>
    </div>
    <div v-else>No hay cursos disponibles.</div>

    <div v-if="modalVisible" class="modal-overlay">
      <div class="modal-content">
        <button class="close-btn" @click="cancelDelete">×</button>
        <p>¿Estás seguro de que deseas eliminar este curso?</p>
        <button @click="deleteCurso(cursoIdBorrar)" class="modal-button">
          Sí, eliminar
        </button>
        <button @click="cancelDelete" class="modal-button">Cancelar</button>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import { useToast } from "vue-toast-notification";

const cursos = ref([]);
const modalVisible = ref(false);
const cursoIdBorrar = ref(null);
const router = useRouter();
const cursosOriginales = ref([]);
const toast = useToast();
const search = ref("");

const fetchCursos = async () => {
  try {
    const response = await axios.get("/api/cursos/");
    cursos.value = response.data;
    cursosOriginales.value = [...response.data];
  } catch (error) {
    console.error("Error fetching cursos:", error);
  }
};
const filteredCursos = computed(() => {
  if (!search.value) {
    return cursos.value;
  }
  const searchLower = search.value.toLowerCase();
  return cursos.value.filter((curso) =>
    curso.nombre.toLowerCase().includes(searchLower)
  );
});

const deleteCurso = async (id) => {
  try {
    await axios.delete(`/api/cursos/borrarCurso/${id}`);
    cursos.value = cursos.value.filter((curso) => curso.id !== id);
    closeModal();
    location.reload();
    toast.success("Curso eliminado exitosamente");
  } catch (error) {
    toast.error("Error borrando el curso");
  }
};

const sortAsignaturasAlphabetically = () => {
  resetFilters();
  cursos.value.sort((a, b) => a.nombre.localeCompare(b.nombre));
};

const resetFilters = () => {
  cursos.value = [...cursosOriginales.value];
};

const applyFilter = (filter) => {
  if (filter === "alfabeticamente") {
    sortAsignaturasAlphabetically();
  } else if (filter === "reset") {
    resetFilters();
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

const cancelDelete = () => {
  closeModal();
  location.reload();
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
  background-color: #f2f2f2;
}

input[type="text"] {
  border-radius: 4px;
  outline: 2px solid #3f9fd6;
  border: none;
  box-shadow: 0px 10px 20px -18px #3f9fd6;
  border-bottom: 3px solid #3f9fd6;
  transition: 0.1s ease;
  height: 30px;
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

.burger {
  display: inline-block;
  cursor: pointer;
  position: relative;
}

.burger input {
  display: none;
}

.burger span {
  display: block;
  width: 25px;
  height: 3px;
  margin: 5px;
  background: #969696;
  transition: 0.3s;
}

.burger input:checked ~ span:nth-child(1) {
  transform: rotate(45deg) translate(5px, 5px);
}

.burger input:checked ~ span:nth-child(2) {
  transform: rotate(45deg) translate(1.5px);
}

.burger input:checked ~ span:nth-child(3) {
  transform: rotate(-45deg) translate(5px, -5px);
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {
  background-color: #f1f1f1;
}

.burger input:checked ~ .dropdown-content {
  display: block;
}

.bodySuperior {
  display: flex;
  justify-content: space-between;
  margin: 30px;
}

.buscadorYfiltros {
  display: flex;
  gap: 10px;
  align-items: center;
}

.aniadir:hover {
  background-color: #1e1a1a;
}

.aniadir {
  background-color: #007bff;
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

.aniadir:hover {
  background-color: #1e1a1a;
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

.modal-button {
  margin: 10px;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1em;
  transition: background-color 0.3s, transform 0.3s;
}

.modal-button {
  background-color: #ff5f5f;
  color: white;
}

.modal-button:hover {
  background-color: #ff1a1a;
  transform: scale(1.05);
}

.modal-button:last-of-type {
  background-color: #ddd;
  color: black;
}

.modal-button:last-of-type:hover {
  background-color: #ccc;
}

@media (max-width: 768px) {
  .cards-container {
    flex-direction: column;
    align-items: center;
  }

  .card {
    width: 100%;
  }

  .bodySuperior {
    flex-direction: column;
    align-items: center;
  }

  .aniadir {
    margin-top: 20px;
    align-items: center;
  }

  .burger {
    order: 1;
  }

  .buscador {
    order: 2;
  }
}
</style>
