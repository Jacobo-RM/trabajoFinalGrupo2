<template>
  <div>
    <div class="addBtn">
      <button
        class="aniadir"
        @click="goToCreateAsignatura"
        title="Crear nueva asignatura"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 24 24"
          width="50"
          height="24"
          color="#FFFFFF"
          fill="none"
        >
          <path
            d="M14.0022 4.5C14.3777 4.16667 14.6984 4 15.0682 4C15.7664 4 16.2898 4.59409 17.3368 5.78228L19.9115 8.70448C21.3038 10.2847 22 11.0747 22 12C22 12.9253 21.3038 13.7153 19.9115 15.2955L17.3368 18.2177C16.2898 19.4059 15.7664 20 15.0682 20C14.6984 20 14.3777 19.8333 14.0022 19.5"
            stroke="currentColor"
            stroke-width="1.5"
            stroke-linecap="round"
          />
          <path
            d="M7 5.4398C7.86196 4.47993 8.35392 4 8.98862 4C9.69478 4 10.2243 4.59409 11.2832 5.78228L13.8875 8.70448C15.2959 10.2847 16 11.0747 16 12C16 12.9253 15.2959 13.7153 13.8876 15.2955L11.2832 18.2177C10.2243 19.4059 9.69478 20 8.98862 20C8.35392 20 7.86196 19.5201 7 18.5602"
            stroke="currentColor"
            stroke-width="1.5"
            stroke-linecap="round"
          />
          <path
            d="M5.5 15.5L5.5 8.5M2 12H9"
            stroke="currentColor"
            stroke-width="1.5"
            stroke-linecap="round"
          />
        </svg>
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
          <p class="card-title">
            {{ asignatura.nombre }}
          </p>
          <p class="card-text">{{ asignatura.descripcion }}</p>
          <div class="card-actions">
            <button
              @click="goToEditAsignatura(asignatura.id)"
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
              @click="confirmDelete(asignatura.id)"
              title="borrar"
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
            <button
              @click="showDetails(asignatura)"
              class="btn btn-secondary"
              title="Ver detalles"
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
          </div>
        </div>
      </div>
    </div>
    <div v-else>No hay asignaturas disponibles.</div>

    <div v-if="modalVisible" class="modal-overlay">
      <div class="modal-content">
        <button class="close-btn" @click="modalVisible = false">×</button>
        <h2>Detalles</h2>
        <p><strong>Nombre:</strong> {{ selectedAsignatura.nombre }}</p>
        <p>
          <strong>Descripción:</strong> {{ selectedAsignatura.descripcion }}
        </p>
        <p><strong>Curso:</strong> {{ selectedAsignatura.curso.nombre }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

export default {
  setup() {
    const asignaturas = ref([]);
    const modalVisible = ref(false);
    const selectedAsignatura = ref(null);
    const router = useRouter();

    const fetchAsignaturas = async () => {
      try {
        const response = await axios.get("/api/cursos/asignaturas");
        asignaturas.value = response.data;
      } catch (error) {
        console.error("Error fetching asignaturas:", error);
      }
    };

    const deleteAsignatura = async (id) => {
      try {
        const confirmed = confirm(
          "¿Estás seguro de que deseas eliminar esta asignatura?"
        );
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
      router.push("/asignatura-form");
    };

    const goToEditAsignatura = (id) => {
      router.push(`/asignatura-form/${id}`);
    };

    onMounted(() => {
      fetchAsignaturas();
      console.log(asignaturas);
    });

    return {
      asignaturas,
      confirmDelete,
      modalVisible,
      selectedAsignatura,
      showDetails,
      goToCreateAsignatura,
      goToEditAsignatura,
    };
  },
};
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
</style>
