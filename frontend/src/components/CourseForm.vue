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
          <input type="date" id="fechaInicio" v-model="curso.fechaInicio" required>
        </div>
        <div class="form-group">
          <label for="fecha_fin">Fecha Fin</label>
          <input type="date" id="fechaFin" v-model="curso.fechaAFin" required>
        </div>
        <div class="form-group">
          <label for="precio">Precio</label>
          <input type="number" id="precio" v-model="curso.precio" required>
        </div>
        <button type="submit">Guardar</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref, defineEmits } from 'vue';
  
  const emit = defineEmits(['submit']);
  
  const curso = ref({
    nombre: '',
    descripcion: '',
    fechaInicio: '',
    fechaFin: '',
    precio: 0
  });
  
  const submitForm = () => {
    const { fechaInicio, fechaFin } = curso.value;
  
    const isValidDate = (dateStr) => !isNaN(new Date(dateStr).getTime());
    
    const safeFechaInicio = isValidDate(fechaInicio) ? new Date(fechaInicio).toISOString() : null;
    const safeFechaFin = isValidDate(fechaFin) ? new Date(fechaFin).toISOString() : null;
  
    const cursoData = {
      ...curso.value,
      fechaInicio: safeFechaInicio,
      fechaFin: safeFechaFin
    };
  
    emit('submit', cursoData);
  
    curso.value = {
      nombre: '',
      descripcion: '',
      fechaInicio: '',
      fechaFin: '',
      precio: 0
    };
  };
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