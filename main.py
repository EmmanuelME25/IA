import pygame
import sys
import numpy
import time
from random import seed
from random import randint

pygame.init()

clock = pygame.time.Clock()

red = (255, 0, 0)
green = (0, 255, 0)
blue = (0, 0, 255)
black = (0, 0, 0)
white = (255, 255, 255)

n_reynas = 15
dim = 50
screenx = n_reynas * dim
screeny = n_reynas * dim
screen = pygame.display.set_mode((screenx, screeny))
screen.fill(black)
n_sujetos = 20  # debe ser mayor o igual 4

e_f = numpy.zeros(n_sujetos, dtype=int)
e_c = numpy.zeros((n_sujetos))
e_dp = numpy.zeros((n_sujetos))
e_dn = numpy.zeros((n_sujetos))
t_reinas = numpy.zeros((n_sujetos))

e_fin = numpy.zeros(n_sujetos, dtype=int)
e_cin = numpy.zeros((n_sujetos))
e_dpin = numpy.zeros((n_sujetos))
e_dnin = numpy.zeros((n_sujetos))

e_total = numpy.zeros(n_sujetos, dtype=int)
fitness = numpy.zeros(n_sujetos, dtype=int)
porcentaje_de_vivir = numpy.zeros(n_sujetos, dtype=float)

queen = pygame.image.load("queen.png")
queen = pygame.transform.scale(queen, (dim, dim))

poblacion = numpy.zeros((n_sujetos, n_reynas, n_reynas), dtype=int)
# poblacion(profundidad,fila,columnas)

ns = 0
nf = 0
nc = 0

for ns in range(n_sujetos):
    for nc in range(0, n_reynas):
        poblacion[ns, randint(0, n_reynas - 1), nc] = 1


def drawreynas(sujeto):
    for nf in range(n_reynas):
        for nc in range(n_reynas):
            if poblacion[sujeto, nf, nc] == 1:
                screen.blit(queen, (nc * dim, nf * dim))
    pygame.display.flip()


def tablero(color):
    x = 0
    y = 0
    screen.fill(color)
    pygame.display.flip()
    for y in range(0, n_reynas):
        for x in range(0, n_reynas):
            if (x % 2 != 0 and y % 2 == 0):
                pygame.draw.rect(screen, white, (x * dim, y * dim, dim, dim))
            if (x % 2 == 0 and y % 2 != 0):
                pygame.draw.rect(screen, white, (x * dim, y * dim, dim, dim))


tablero(red)
drawreynas(0)

pygame.display.set_caption('ALGORITMO GENETICO N REINAS')

pygame.display.flip()
close = False
generacion = 0
fmax = 4 * (n_reynas - 1)
while (close == False):
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            close = True
            # iniciar reset errores
    for ns in range(n_sujetos):
        e_f[ns] = 0
        e_c[ns] = 0
        e_dp[ns] = 0
        e_dn[ns] = 0
        t_reinas[ns] = 0

        e_fin[ns] = 0
        e_cin[ns] = 0
        e_dpin[ns] = 0
        e_dnin[ns] = 0
    # EVALUACION DE FILAS
    for ns in range(n_sujetos):

        for nf in range(n_reynas):

            e_fin[ns] = e_f[ns]

            for nc in range(n_reynas):
                if poblacion[ns, nf, nc] == 1:
                    e_f[ns] += 1
            if e_f[ns] - e_fin[ns] != 0:
                e_f[ns] -= 1
    # evaluacion de columnas
    for ns in range(n_sujetos):

        for nc in range(n_reynas):

            e_cin[ns] = e_c[ns]

            for nf in range(n_reynas):
                if poblacion[ns, nf, nc] == 1:
                    e_c[ns] += 1
            if e_c[ns] - e_cin[ns] != 0:
                e_c[ns] -= 1
    # evaluacion diagonal positiva

    for ns in range(n_sujetos):
        dp = 0
        while dp <= ((n_reynas - 1) * 2):
            e_dpin[ns] = e_dp[ns]
            for nf in range(n_reynas):
                for nc in range(n_reynas):
                    if (nf + nc) == dp:
                        if poblacion[ns, nf, nc] == 1:
                            e_dp[ns] += 1
            if e_dp[ns] - e_dpin[ns] != 0:
                e_dp[ns] -= 1
            dp += 1

    # evaluacion en diagonal negativa

    for ns in range(n_sujetos):
        dn = 1 - n_reynas
        while dn <= n_reynas - 1:
            e_dnin[ns] = e_dn[ns]
            for nf in range(n_reynas):
                for nc in range(n_reynas):
                    if poblacion[ns, nf, nc] == 1 and (nc - nf) == dn:
                        e_dn[ns] += 1
            if e_dn[ns] - e_dnin[ns] != 0:
                e_dn[ns] -= 1
            dn += 1

    # evaluacion de total de reinas
    for ns in range(n_sujetos):
        for nf in range(n_reynas):
            for nc in range(n_reynas):
                if poblacion[ns, nf, nc] == 1:
                    t_reinas[ns] += 1
    # fitness

    for ns in range(n_sujetos):
        e_total[ns] = e_f[ns] + e_c[ns] + e_dp[ns] + e_dn[ns]
        fitness[ns] = (fmax - e_total[ns]) + abs(0 - t_reinas[ns])

    # porcentajes de vivir
    porcentaje_total = 0

    for ns in range(n_sujetos):
        porcentaje_total += fitness[ns]

    for ns in range(n_sujetos):
        porcentaje_de_vivir[ns] = float((fitness[ns] * 100) / porcentaje_total)

    # mejores padres
    p_padre1 = 0
    p_padre2 = 0
    # encontrar padre 1
    for ns in range(n_sujetos):
        if porcentaje_de_vivir[ns] >= porcentaje_de_vivir[p_padre1]:
            p_padre1 = ns
            ns = n_sujetos

    if generacion == 10000:
        close = True
    for ns in range(n_sujetos):
        if (fmax - e_total[ns]) + abs(t_reinas[ns]) == n_reynas + fmax:
            close = True
            tablero(green)
            drawreynas(p_padre1)
            print("GENERACION: ", generacion)
            # print(e_c)
            # print(e_f)
            # print(e_dp)
            # print(e_dn)
            # print(t_reinas)
    if close == False and generacion % 5 == 0:
        print("GENERACION: ", generacion)
        tablero(red)
        drawreynas(0)

    # padre 2

    porcentaje_de_vivir[p_padre1] = 0

    for ns in range(n_sujetos):
        if porcentaje_de_vivir[ns] >= porcentaje_de_vivir[p_padre2] and (
                poblacion[p_padre1, :, :] != poblacion[ns, :, :]).any():
            p_padre2 = ns
            ns = n_sujetos

    hijo = numpy.zeros((3, n_reynas, n_reynas), dtype=int)
    mejorsujeto = numpy.zeros((n_reynas, n_reynas), dtype=int)
    cruce = n_reynas - 1
    for _ in range(randint(1, n_reynas)):
        cruce = randint(1, n_reynas - 1)
    # cruce 1 gen
    # cruce=3
    for nf in range(n_reynas):
        for nc in range(n_reynas):
            if nc < cruce:
                hijo[0, nf, nc] = poblacion[p_padre1, nf, nc]
                hijo[1, nf, nc] = poblacion[p_padre1, nf, nc]
            if nc >= cruce:
                hijo[2, nf, nc] = poblacion[p_padre1, nf, nc]
            mejorsujeto[nf, nc] = poblacion[p_padre1, nf, nc]
    # cruce 2 gen
    for nf in range(n_reynas):
        for nc in range(n_reynas):
            if nc >= cruce:
                hijo[0, nf, nc] = poblacion[p_padre1, nf, nc]
                hijo[1, nf, nc] = poblacion[p_padre2, nf, nc]
            if nc < cruce:
                hijo[2, nf, nc] = poblacion[p_padre2, nf, nc]
    # mutacion en hijo 1 # y sujetos si es mayor a 5
    for _ in range(2):
        for _ in range(randint(1, n_reynas)):
            nc = randint(0, n_reynas - 1)
            nf = randint(0, n_reynas - 1)
        if hijo[0, nf, nc] == 1:
            hijo[0, nf, nc] = 0
        else:
            hijo[0, nf, nc] = 1

    for nf in range(n_reynas):
        for nc in range(n_reynas):
            poblacion[0, nf, nc] = mejorsujeto[nf, nc]
            poblacion[1, nf, nc] = hijo[0, nf, nc]
            poblacion[2, nf, nc] = hijo[1, nf, nc]
            poblacion[3, nf, nc] = hijo[2, nf, nc]

    if n_sujetos >= 5:
        for ns in range(4, n_sujetos):
            for nf in range(n_reynas):
                for nc in range(n_reynas):
                    poblacion[ns, nf, nc] = mejorsujeto[nf, nc]

    if n_sujetos >= 5:
        for _ in range(randint(1, 2)):
            for ns in range(4, n_sujetos):
                for _ in range(randint(1, n_reynas)):
                    nc = randint(0, n_reynas - 1)
                    nf = randint(0, n_reynas - 1)
                if poblacion[ns, nf, nc] == 1:
                    poblacion[ns, nf, nc] = 0
                else:
                    poblacion[ns, nf, nc] = 1
    # print(geracion)
    generacion += 1

close = False

while (close == False):
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            close = True

pygame.quit()
