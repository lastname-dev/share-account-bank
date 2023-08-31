import { axiosInstance } from "./index";

const userAPI = {
  signup: (id, account, phone, name, password, passwordCheck) =>
    axiosInstance.post("/users", { id, account, phone, name, password, passwordCheck }),

  getMyInformation: () => axiosInstance.get("/users"),

  editMyInformation: (myInfoData) => axiosInstance.put("/users", myInfoData),

  login: (id, password) => axiosInstance.post("/login", { id, password }),

  logout: () => axiosInstance.post("/logout"),

  editPassword: (id, name, password, passwordCheck) =>
    axiosInstance.put("/password", { id, name, password, passwordCheck }),
};

export default userAPI;
