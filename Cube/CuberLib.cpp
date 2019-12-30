#define CuberLib __declspec(dllexport)

#include "iostream"
#include "string"

static const int sides = 6;
static const int e_ordr[] = {1, 3, 0, 4, 5, 2};
static const int m_ordr[] = {3, 2, 0, 1, 5, 4};
static const int s_ordr[] = {5, 3, 1, 4, 2, 0};
static const std::string color_map[] = {"W", "G", "B", "R", "O", "Y"};

extern "C" CuberLib int getLen(int dim) {
    return (sides * dim * dim);
}

extern "C" CuberLib void init(char *_cube, int dim)
{
    for (int i = 0; i < (sides * dim * dim); i++)
    {
        _cube[i] = i / (dim * dim);
    }
}

char &get(char *_cube, int dim, int face, int y, int x)
{
    return _cube[face * dim * dim + y * dim + x];
}

char &e_get(char *_cube, int dim, int face, int y, int x)
{
    return get(_cube, dim, e_ordr[face], y, x);
}

char &m_get(char *_cube, int dim, int face, int y, int x)
{
    return ((m_ordr[face] == 5) ? get(_cube, dim, m_ordr[face], x, (dim - y - 1)) : get(_cube, dim, m_ordr[face], (dim - x - 1), y));
}

char &s_get(char *_cube, int dim, int face, int y, int x)
{
    switch (s_ordr[face])
    {
    default:
    case 0:
    case 1:
        return get(_cube, dim, s_ordr[face], y, x);
    case 2:
    case 5:
        return get(_cube, dim, s_ordr[face], (dim - y - 1), (dim - x - 1));
    case 3:
        return get(_cube, dim, s_ordr[face], (dim - x - 1), y);
    case 4:
        return get(_cube, dim, s_ordr[face], x, (dim - y - 1));
    }
}

void _turn(char *_cube, int dim, int index, char& (*getFunc)(char*, int, int, int, int))
{
    char *clone = new char[sides * dim * dim];
    for (int i = 0; i < (sides * dim * dim); i++)
    {
        clone[i] = _cube[i];
    }
    

    for (int i = 1; i < (sides - 1); i++)
    {
        for (int j = 0; j < dim; j++)
        {
            (*getFunc)(_cube, dim, i, index, j) = (*getFunc)(clone, dim, ((i % 4) + 1), index, j);
        }
    }

    if (index == 0) {
        for (int y = 0; y < dim; y++)
        {
            for (int x = 0; x < dim; x++)
            {
                (*getFunc)(_cube, dim, 0, y, x) = (*getFunc)(clone, dim, 0, (dim - x - 1), y);
            }
            
        }
    } else if (index == (dim - 1)) {
        for (int y = 0; y < dim; y++)
        {
            for (int x = 0; x < dim; x++)
            {
                (*getFunc)(_cube, dim, (sides - 1), y, x) = (*getFunc)(clone, dim, (sides - 1), x, (dim - y - 1));
            }
        }
    }
}

extern "C" CuberLib void turn(char *_cube, int dim, int index) {
    if (index < dim) {
        _turn(_cube, dim, index, e_get);
    } else if (index < dim * 2) {
        _turn(_cube, dim, (index % dim), m_get);
    } else {
        _turn(_cube, dim, (index % dim), s_get);
    }
}

extern "C" CuberLib void turns(char *_cube, int dim, int *inds, int len) {
    for (int i = 0; i < len; i++)
    {
        turn(_cube, dim, inds[i]);
    }
}

extern "C" CuberLib void disp(char *_cube, int dim)
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

    for (int i = 0; i < sides; i++)
    {
        for (int j = 0; j < dim; j++)
        {
            int line = j + dim * (((i % 5) == 0) ? 2 * (i / 5) : 1);
            for (int k = 0; k < dim; k++)
            {
                lines[line].append(color_map[(int)e_get(_cube, dim, i, j, k)]);
                lines[line].append(((k == (dim - 1)) ? "  " : ", "));
            }
        }
    }

    for (int i = 0; i < dim * 3; i++)
    {
        std::cout << lines[i] << std::endl;
    }
}

extern "C" CuberLib int compare(char *_cube1, char *_cube2, int dim) {
    int diff = 0;
    for (int i = 0; i < (sides * dim * dim); i++)
    {
        diff += (_cube1[i] == _cube2[i]);
    }
    return diff;
}