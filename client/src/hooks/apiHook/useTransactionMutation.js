import { useMutation } from "@tanstack/react-query";
import bankAPI from "apis/bank";

export const useTransactionMutation = () => {
  const transactMutation = useMutation({
    mutationFn: bankAPI.transactAccount,
  });

  return transactMutation;
};
