import * as S from "./ProgressBar.style";

const ProgressBar = ({ goalMoney, currentMoney }) => {
  const currentPercent = String((currentMoney / goalMoney) * 100);
  return (
    <S.ProgressBarWrapper>
      <S.Bar $percent={currentPercent}>{currentPercent > 10 && currentPercent + "%"}</S.Bar>
    </S.ProgressBarWrapper>
  );
};

export default ProgressBar;
