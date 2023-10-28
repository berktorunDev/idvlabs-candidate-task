import axios from "axios";

const BASE_URL_STOCK = "http://localhost:8080/api/public/stocks";

const api = axios.create({
  baseURL: BASE_URL_STOCK,
});

const updateStock = (id, stockData) => api.put(`/update/${id}`, stockData);

const createStock = (stockData) => api.post("/create", stockData);

const getStockById = (id) => api.get(`/${id}`);

const getAllStocks = () => api.get("/getAll");

const deleteStock = (id) => api.delete(`/delete/${id}`);

export default {
  updateStock,
  createStock,
  getStockById,
  getAllStocks,
  deleteStock,
};
