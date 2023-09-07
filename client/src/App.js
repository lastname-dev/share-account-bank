import React, { Suspense } from "react";
import AppRouter from "router/AppRouter";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { RecoilRoot } from "recoil";

function App() {
  const queryClient = new QueryClient({
    defaultOptions: {
      queries: {
        retry: 2,
        suspense: true,
        useErrorBoundary: true,
      },
    },
  });

  return (
    <React.Fragment>
      <QueryClientProvider client={queryClient}>
        <Suspense fallback={<h1>Loading...</h1>}>
          <RecoilRoot>
            <AppRouter />
          </RecoilRoot>
        </Suspense>
      </QueryClientProvider>
    </React.Fragment>
  );
}

export default App;
