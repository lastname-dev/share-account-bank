import { useMutation } from "@tanstack/react-query";
import businessAPI from "apis/business";
import { PATH } from "constants/path";
import { useNavigate } from "react-router-dom";
import { toastError } from "utils/toast";

export const useStratTravelMutation = (groupId) => {
  const navigate = useNavigate();

  const stratTravelMutation = useMutation({
    mutationFn: () => businessAPI.startTravel(groupId),
    onSuccess: () => navigate(PATH.TRAVELING_PAGE(groupId)),
    onError: () => toastError("요청 전송 실패!"),
  });

  return stratTravelMutation;
};
