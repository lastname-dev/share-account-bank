import { useCallback, useEffect, useState } from "react";
import { useTravelLogsQuery } from "hooks/apiHook/useTravelLogsQuery";
import * as S from "pages/TravelLogPage/TravelLogPage.style";

const TravelLogPage = () => {
  const travelLogsQuery = useTravelLogsQuery();
  const logList = travelLogsQuery?.travelLogsData?.data;

  const convertDateToText = useCallback((date) => {
    const dateArr = date.split("-");
    return String(dateArr[0]) + "년 " + String(parseInt(dateArr[1])) + "월의 추억";
  }, []);

  return (
    <S.TravelLogPageWrapper>
      <S.TravelLogTitle>추억을 되돌아 볼까요? </S.TravelLogTitle>
      <S.TravelLogList>
        {logList?.map((item, idx) => (
          <S.TravelCard key={item.travelId}>
            <S.ImageContainer>
              <S.TravelImage src={item.photo} />
              <S.TravelCardInfoContiner>
                <S.TravelTitle>{item.groupName}</S.TravelTitle>
                <S.TravelContent>{convertDateToText(item.startDate)}</S.TravelContent>
              </S.TravelCardInfoContiner>
            </S.ImageContainer>
          </S.TravelCard>
        ))}
      </S.TravelLogList>
    </S.TravelLogPageWrapper>
  );
};

export default TravelLogPage;
