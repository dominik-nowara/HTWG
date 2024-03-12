I=[-1,3];
N = 3;
x=linspace(I(1),I(2),100);
x0 = 0;

Tf = 0;
for k=0:N
    Tf = Tf + DfuncSym(x0,k)/factorial(k)*(x-x0).^k;
end


%%
plot(x,DfuncSym(x,0),'k-')
grid on
hold on
% plot(x0,Dfunc(x0,0),'ro')
 plot(x,Tf,'r-')
xlim(I);
ylim([-2,4])
set(gca, 'XAxisLocation', 'origin', 'YAxisLocation', 'origin')

function y=Dfunc(x,k)

switch k
    case 0
        y = log(2-3*x+x.^2)
    case 1
        y = (2*x - 3)./(x.^2 - 3*x + 2)
    case 2
        y = -(2*x.^2 - 6*x + 5)./(x.^2 - 3*x + 2).^2;
    case 3
        y = (2*(2*x.^3 - 9*x.^2 + 15*x - 9))./(x.^2 - 3*x + 2).^3;
end

end

function y=DfuncSym(x,k)
syms s

f(s) = log(1./a*x+b);
for i=1:k
    f(s) = diff(f(s),s);
end

y = subs(f(s),s=x);

end