import Receipt from "components/@common/Receipt/Receipt";
import { useFinishTravelQuery } from "hooks/apiHook/useFinishTravelQuery";
import * as S from "pages/TripResultPage/TripResultPage.style";
import { useParams } from "react-router-dom";
import { setMoneyRegex } from "utils/regex";

const TripResultPage = () => {
  const { groupId } = useParams();
  // const { finishTravelData } = useFinishTravelQuery(groupId);

  const finishTravelData = {
    accountId: "110-321-123459", // 모임 계좌
    balance: 300000, // 잔고
    goal: 4000000, // 목표 금액
    startDate: "2023-09-05", // 여행 시작 날짜
    endDate: "2023-09-11", // 여행 종료 날짜
    limitMember: 2, // 참여 인원
    historyList: [
      {
        time: "2023-09-05",
        sender: "String",
        receiver: "String",
        amount: 30000,
        type: "send",
      },
      {
        time: "2023-09-06",
        sender: "String",
        receiver: "String",
        amount: 5000,
        type: "send",
      },
    ], // 이체내역
    participants: [
      {
        userName: "하잉",
        email: "stirng",
      },
      {
        userName: "할로",
        email: "stirng",
      },
    ], // 참여자 목록
  };

  return (
    <S.TripResultPageWrapper>
      <Receipt accountData={finishTravelData}>
        <S.groupInfoContainer>
          <span>목표금액</span>
          <span>{setMoneyRegex(finishTravelData.goal)}원</span>
        </S.groupInfoContainer>
        <S.groupInfoContainer>
          <span>참여인원</span>
          <span>{finishTravelData.limitMember}명</span>
        </S.groupInfoContainer>
      </Receipt>
      <S.TripCalculationButton>정산하기</S.TripCalculationButton>
    </S.TripResultPageWrapper>
  );
};

export default TripResultPage;
