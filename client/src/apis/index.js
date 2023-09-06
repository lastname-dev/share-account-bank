import axios from "axios";
import { getSession } from "utils/session";

export const axiosInstance = axios.create({
  baseURL: process.env.REACT_APP_SERVER_URL,
  headers: {
    "Content-Type": "application/json",
    Authorization: `${getSession("Authorization")}`,
  },
});


