import Receipt from "components/@common/Receipt/Receipt";
import { useFinishTravelQuery } from "hooks/apiHook/useFinishTravelQuery";
import * as S from "pages/TripResultPage/TripResultPage.style";
import { useParams } from "react-router-dom";

const TripResultPage = () => {
  const { groupId } = useParams();
  // const { finishTravelData } = useFinishTravelQuery(groupId);

  return (
    <S.TripResultPageWrapper>
      <Receipt>l</Receipt>
      <S.TripCalculationButton>정산하기</S.TripCalculationButton>
    </S.TripResultPageWrapper>
  );
};

export default TripResultPage;
