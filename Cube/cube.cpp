#define cube __declspec(dllexport)

#include "iostream"
#include "string"

static const int sides = 6;
static const int e_ordr[] = {1, 3, 0, 4, 5, 2};
static const int m_ordr[] = {3, 2, 0, 1, 5, 4};
static const int s_ordr[] = {5, 3, 1, 4, 2, 0};
static const std::string color_map[] = {"W", "G", "B", "R", "O", "Y"};

static char *_cube;
static char *_cube_clone;
static int len;
static int dim;

extern "C" cube void init(int _dim)
{
    dim = _dim;
    len = sides * dim * dim;
    _cube = new char[len];
    _cube_clone = new char[len];

    for (int i = 0; i < len; i++)
    {
        _cube[i] = i / (dim * dim);
    }
}

char &get(int face, int y, int x, bool is_clone)
{
    return (is_clone ?_cube_clone[face * dim * dim + y * dim + x] : _cube[face * dim * dim + y * dim + x]);
}

char &e_get(int face, int y, int x, bool is_clone)
{
    return get(e_ordr[face], y, x, is_clone);
}

char &m_get(int face, int y, int x, bool is_clone)
{
    return ((m_ordr[face] == 5) ? get(m_ordr[face], x, (dim - y - 1), is_clone) : get(m_ordr[face], (dim - x - 1), y, is_clone));
}

char &s_get(int face, int y, int x, bool is_clone)
{
    switch (s_ordr[face])
    {
    case 0:
    case 1:
        return get(s_ordr[face], y, x, is_clone);
    case 2:
    case 5:
        return get(s_ordr[face], (dim - y - 1), (dim - x - 1), is_clone);
    case 3:
        return get(s_ordr[face], (dim - x - 1), y, is_clone);
    case 4:
        return get(s_ordr[face], x, (dim - y - 1), is_clone);
    }
}

void regenCloneCube() {
    for (int i = 0; i < len; i++)
    {
        _cube_clone[i] = _cube[i];
    }
}

extern "C" cube void e_turn(int index)
{
    regenCloneCube();
    for (int i = 1; i < (sides - 1); i++)
    {
        for (int j = 0; j < dim; j++)
        {
            e_get(i, index, j, false) = e_get(((i % 4) + 1), index, j, true);
        }
    }

    if (index == 0) {
        for (int y = 0; y < dim; y++)
        {
            for (int x = 0; x < dim; x++)
            {
                e_get(0, y, x, false) = e_get(0, (dim - x - 1), y, true);
            }
            
        }
    } else if (index == (dim - 1)) {
        for (int y = 0; y < dim; y++)
        {
            for (int x = 0; x < dim; x++)
            {
                e_get(0, y, x, false) = e_get(0, x, (dim - y - 1), true);
            }
        }
    }
}

extern "C" cube void m_turn(int index)
{
    regenCloneCube();
    for (int i = 1; i < (sides - 1); i++)
    {
        for (int j = 0; j < dim; j++)
        {
            m_get(i, index, j, false) = m_get(((i % 4) + 1), index, j, true);
        }
    }

    if (index == 0) {
        for (int y = 0; y < dim; y++)
        {
            for (int x = 0; x < dim; x++)
            {
                m_get(0, y, x, false) = m_get(0, (dim - x - 1), y, true);
            }
            
        }
    } else if (index == (dim - 1)) {
        for (int y = 0; y < dim; y++)
        {
            for (int x = 0; x < dim; x++)
            {
                m_get(0, y, x, false) = m_get(0, x, (dim - y - 1), true);
            }
        }
    }
}

extern "C" cube void s_turn(int index)
{
    regenCloneCube();
    for (int i = 1; i < (sides - 1); i++)
    {
        for (int j = 0; j < dim; j++)
        {
            s_get(i, index, j, false) = s_get(((i % 4) + 1), index, j, true);
        }
    }

    if (index == 0) {
        for (int y = 0; y < dim; y++)
        {
            for (int x = 0; x < dim; x++)
            {
                s_get(0, y, x, false) = s_get(0, (dim - x - 1), y, true);
            }
            
        }
    } else if (index == (dim - 1)) {
        for (int y = 0; y < dim; y++)
        {
            for (int x = 0; x < dim; x++)
            {
                s_get(0, y, x, false) = s_get(0, x, (dim - y - 1), true);
            }
        }
    }
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

    for (int i = 0; i < sides; i++)
    {
        for (int j = 0; j < dim; j++)
        {
            int line = j + dim * (((i % 5) == 0) ? 2 * (i / 5) : 1);
            for (int k = 0; k < dim; k++)
            {
                lines[line].append(color_map[(int)e_get(i, j, k, false)]);
                lines[line].append(((k == (dim - 1)) ? "  " : ", "));
            }
        }
    }

    for (int i = 0; i < dim * 3; i++)
    {
        std::cout << lines[i] << std::endl;
    }
}