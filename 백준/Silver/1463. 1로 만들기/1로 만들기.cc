#include <iostream>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int N;
    cin >> N;

    int D[1000001];
    D[1] = 0;
    for (int i = 2; i <= N; i++){
        D[i] = D[i-1] + 1;
        if (i % 3 == 0)
            D[i] = min(D[i], D[i/3]+1);
        if (i % 2 == 0)
            D[i] = min(D[i], D[i/2]+1);
    }
    cout << D[N] << '\n';
    return 0; 
}