#define cube __declspec(dllexport)

#include "iostream"
#include "string"

static char *_cube;
static int len;
static int dim;

extern "C" cube void init(int _dim)
{
    dim = _dim;
    len = 6 * dim * dim;
    _cube = new char[len];

    for (int i = 0; i < len; i++)
    {
        _cube[i] = i / (dim * dim);
    }
}

char get(int face, int y, int x)
{
    return _cube[face * dim * dim + y * dim + x];
}

extern "C" cube void disp()
{
    std::string *lines = new std::string[dim * 3];
    for (int i = 0; i < dim * 3; i++)
    {
        lines[i] = "";
        if ((i < dim) || (i >= dim * 2))
        {
            for (int j = 0; j < dim; j++)
            {
                lines[i] += "   ";
            }
        }
    }

    for (int i = 0; i < 6; i++)
    {
        for (int j = 0; j < dim; j++)
        {
            int line = j + dim * (((i % 5) == 0) ? 2 * (i / 5) : 1);
            for (int k = 0; k < dim; k++)
            {
                lines[line].append(std::to_string((int)get(i, j, k)));
                lines[line].append(((k == (dim - 1)) ? "  " : ", "));
            }
        }
    }

    for (int i = 0; i < dim * 3; i++)
    {
        std::cout << lines[i] << std::endl;
    }
}