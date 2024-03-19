import axios from "axios";

const baseUrl = axios.create({
     baseURL : "http://localhost:8080/pos/api"
})

export {baseUrl}