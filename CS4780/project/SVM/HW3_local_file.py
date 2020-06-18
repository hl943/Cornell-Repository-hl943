# SVM project. Classify XOR data using kernelized SVM 

## Importing packages

import numpy as np
from numpy.matlib import repmat
import sys
import time
import cvxpy as cp
from cvxpy import *
import l2distance
import visclassifier
import matplotlib
import matplotlib.pyplot as plt
from scipy.stats import linregress
import pylab
from matplotlib.animation import FuncAnimation

## End importing package


# Defining the generating random data

def genrandomdata(n=100,b=0.):
    # generate random data and linearly separagle labels
    xTr = np.random.randn(n, 2)
    # defining random hyperplane
    w0 = np.random.rand(2, 1)
    # assigning labels +1, -1 labels depending on what side of the plane they lie on
    yTr = np.sign(np.dot(xTr, w0)+b).flatten()
    return xTr, yTr


# Defining the primalSVM
    
def primalSVM(xTr, yTr, C=1):
    N, d = xTr.shape
    y = yTr.flatten()
    
    w = Variable(d)
    b = Variable(1)
    xi = Variable(N, nonneg=True)
    objective = sum_squares(w) + C*sum(xi)
    constraints = [multiply(y, xTr@w + b) + xi >= 1]
    prob = Problem(Minimize(objective), constraints)
    prob.solve()
    wout = w.value
    bout = b.value   
  
    fun = lambda x: x.dot(wout) + bout
    return fun, wout, bout

# Testing primalSVM
def arrayify(x):
    """flattens and converts to numpy"""
    return np.array(x).flatten()    

xTr,yTr=genrandomdata()
fun,w,b=primalSVM(xTr,yTr,C=10)
visclassifier.visclassifier(fun,xTr,yTr,w=w,b=b)


err=np.mean(arrayify(np.sign(fun(xTr)))!=yTr)
print("Training error: %2.1f%%" % (err*100))

