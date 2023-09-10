import { useMutation } from "@tanstack/react-query";
import businessAPI from "apis/business";

export const useTravelCommentMutation = (groupId) => {
  const travelCommentMutation = useMutation({
    mutationFn: ({ groupId, formData, config }) => businessAPI.postTravelComment(groupId, formData, config),
    onSuccess: () => {},
    onError: () => {},
  });

  return travelCommentMutation;
};
