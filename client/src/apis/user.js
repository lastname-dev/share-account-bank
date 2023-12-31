import { axiosInstance } from "./index";

const userAPI = {
  signup: (signupForm) => axiosInstance.post("/signup", signupForm),

  login: (loginForm) => axiosInstance.post("/login", loginForm),

  signout: () => axiosInstance.delete("/signout"),

  getMyInformation: () => axiosInstance.get("/users"),

  editMyInformation: (phone, currentPassword, newPassword, newPasswordCheck) =>
    axiosInstance.put("/users", {
      phone,
      currentPassword,
      newPassword,
      newPasswordCheck,
    }),

  logout: () => axiosInstance.post("/logout"),

  editPassword: (id, name, password, passwordCheck) =>
    axiosInstance.put("/password", {
      id,
      name,
      password,
      passwordCheck,
    }),

  sendEmail: (id) => axiosInstance.post("/email", id),

  verifyCode: (code) => axiosInstance.post("/email/verification", code),

  sendAccount: (id) => axiosInstance.post("/accounts/won", id),

  verifyAccount: (accountsNumber, code) => axiosInstance.post("/accounts/won/verification", accountsNumber, code),
};

export default userAPI;
