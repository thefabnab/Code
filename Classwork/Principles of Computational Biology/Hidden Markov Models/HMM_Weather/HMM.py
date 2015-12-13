#Method which outlays most probably path given a set of observed sequences

def viterbi(obs, states, start_p, trans_p, emit_p):
    V = [{}]
    path = {}
    
    # base cases where t == 0
    for y in states:
        V[0][y] = start_p[y] * emit_p[y][obs[0]]
        path[y] = [y]
    
    # Run Viterbi for t > 0
    for t in range(1, len(obs)):
        V.append({})
        newpath = {}

        for y in states:
            (prob, state) = max((V[t-1][y0] * trans_p[y0][y] * emit_p[y][obs[t]], y0) for y0 in states)
            V[t][y] = prob
            newpath[y] = path[state] + [y]

        path = newpath
    n = 0           
    if len(obs) != 1:
        n = t
    print_dptable(V,obs)
    (prob, state) = max((V[n][y], y) for y in states)
    return (prob, path[state], V)

#Method which prints a dynamic programming table associated with most likely path calculations
def print_dptable(V, obs):
    s = "    " + " ".join(("%7s" % i) for i in obs) + "\n"
    for y in V[0]:
        s += "%.5s: " % y
        s += " ".join("%.7s" % ("%f" % v[y]) for v in V)
        s += "\n"
    print(s)

#Forward algorithm which outputs a summation of all probabilities for paths within a HMM
def forward_alg(obs, states, start_p, trans_p, emit_p, end_st):
    L = len(obs)
 
    fwd = []
    f_prev = {}
    for i, x_i in enumerate(obs):
        f_curr = {}
        for st in states:
            if i == 0:
                prev_f_sum = start_p[st]
            else:
                prev_f_sum = sum(f_prev[k]*trans_p[k][st] for k in states)
            f_curr[st] = emit_p[st][x_i] * prev_f_sum
        
        sum_prob = sum(f_curr.values())
        for st in states:
            f_curr[st] /= sum_prob
        fwd.append(f_curr)
        f_prev = f_curr
 
    p_fwd = sum(f_curr[k]*trans_p[k][end_st] for k in states)/10
 
    return p_fwd

states = ('Rainy', 'Sunny')
obs = ('walk', 'shop', 'clean')
start_p = {'Rainy': 0.6, 'Sunny': 0.4}
trans_p = {
   'Rainy' : {'Rainy': 0.7, 'Sunny': 0.3},
   'Sunny' : {'Rainy': 0.4, 'Sunny': 0.6},
   }
emit_p = {
   'Rainy' : {'walk': 0.1, 'shop': 0.4, 'clean': 0.5},
   'Sunny' : {'walk': 0.6, 'shop': 0.3, 'clean': 0.1},
   }
end = 'Sunny'

table = viterbi(obs, states, start_p, trans_p, emit_p)
table
print("The probability of the sequence: \n {} is: {} \n".format(table[1],table[0]))


forward_probability =(forward_alg(obs, states, start_p, trans_p, emit_p,end))
forward = round(forward_probability,3)

print("And the probability of generating this spequence as described by the forward algorithm is: {}".format(forward))
