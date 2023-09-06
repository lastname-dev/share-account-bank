import * as S from "./CalculationPage.style";

const CalculationPage = () => {
  return (
    <S.CalculationPageWrapper>
      <S.CalculationMessage>남은 여비를 정산해주세요!</S.CalculationMessage>
      <S.CalculationImageContainer>
        <S.CalculationImage src={process.env.PUBLIC_URL + "/image/phone.png"} alt="logo" />
      </S.CalculationImageContainer>
      <S.CalculationInfoText>클릭해서 정산 요청하기</S.CalculationInfoText>
      <S.CalculationButton>확인</S.CalculationButton>
    </S.CalculationPageWrapper>
  );
};

export default CalculationPage;
