import { axiosInstance, axiosShinhanInstance } from "./index";

const shinhanAPI = {
  getExchangeStore: (request) => axiosShinhanInstance.post(`/search/branch/city`, request), // 환전 가능 지점
  getMoneyCode: (request) => axiosShinhanInstance.post(`/search/fx/currencycode`, request), // 통화 코드 목록
  getExchangeRate: (request) => axiosShinhanInstance.post(`/search/fx/discount-rate`, request), // 우대율
  getExpectedMoney: (request) => axiosShinhanInstance.post(`/exchange`, request), // 원화 예상 금액
  getExchangeRateByMoney: (request) => axiosShinhanInstance.post(`/search/fxrate/day`, request), // 통화별 환율
};

export default shinhanAPI;
