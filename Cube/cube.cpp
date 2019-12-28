#define cube __declspec(dllexport)

#include "iostream"

static char* _cube;
static int len;
static int dim;

extern "C" cube void init(int _dim) {
    dim = _dim;
    len = 6 * dim * dim;
    _cube = new char[len];

    for (int i = 0; i < len; i++)
    {
        _cube[i] = i / (dim * dim);
    }
}

extern "C" cube char get(int face, int y, int x) {
    if ((face < 6) && (y < dim) && (x < dim)) {
        return _cube[face * dim * dim + y * dim + x];
    } else {
        return -1;
    }
}

extern "C" cube void disp() {
    for (int i = 0; i < 6; i++)
    {
        for (int j = 0; j < dim; j++)
        {
            for (int k = 0; k < dim; k++)
            {
                std::cout << (int) get(i, j, k) << ((k == (dim - 1)) ? "\n" : ", ");
            }
        }
        std::cout << "\n";
    }
}