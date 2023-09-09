import styled from "styled-components";
import Button from "components/@common/Button/Button";
export const ModalContainer = styled.div`
  display: none;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  z-index: 1000;
  justify-content: center;
  align-items: center;
`;

export const ModalContent = styled.div`
  background-color: white;
  width: 400px;
  height: 400px;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  display: flex;
  flex-direction: column;
`;

export const VisibleModal = styled(ModalContainer)`
  display: flex;
`;
export const LeftButton = styled.button`
  padding: 10px 40px;
  margin-right: 10px;
  background-color: #007bff;
  background-color: #ccc;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
`;
export const RightButton = styled(LeftButton)`
  margin-right: 0;
  background-color: #007bff;
`;

export const ModalButtonWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
`;
