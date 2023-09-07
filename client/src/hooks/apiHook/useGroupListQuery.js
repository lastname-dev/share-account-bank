import { useQuery } from "@tanstack/react-query";
import businessAPI from "apis/business";

export const useGroupListQuery = () => {
  const { data } = useQuery(["groupList"], businessAPI.getGroupList);

  return { groupListData: data };
};
