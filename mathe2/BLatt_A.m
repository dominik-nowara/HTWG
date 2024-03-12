clear all

a = [1 2 3 4 5 6];

Nmax = 100;

n = 1:1:Nmax;

an = (1+1./n).^n;
bn = (1+1./n).^(n+1);

hold on;
%plot(n, an, 'bo');
grid on;
%plot (n, bn, 'mo');

alpha = 0.1;
beta = 10;

%cn = n.^alpha./log(n);
%dn = n.^beta./exp(n);

%plot(n, cn, 'ro');
%plot(n, dn, 'go');

gn = 1./n.*[cos(n);sin(n)];

plot(gn(1,:), gn(2,:), 'ko');
