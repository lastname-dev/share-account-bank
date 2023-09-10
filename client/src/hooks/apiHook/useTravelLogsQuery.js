import { useQuery } from "@tanstack/react-query";
import businessAPI from "apis/business";

export const useTravelLogsQuery = () => {
  const { data } = useQuery(["travelLogs"], businessAPI.getTravelLogs);

  return { travelLogsData: data };
};
