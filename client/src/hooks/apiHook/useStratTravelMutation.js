import { useMutation } from "@tanstack/react-query";
import businessAPI from "apis/business";

export const useStratTravelMutation = (groupId) => {
  const stratTravelMutation = useMutation({
    mutationFn: () => businessAPI.startTravel(groupId),
    onSuccess: () => {},
    onError: () => {
      alert("요청 전송 실패!");
    },
  });

  return stratTravelMutation;
};
