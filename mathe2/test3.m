clear;

% Parameter
A = 2/3;
T = 9/4;

% Definition der Fresnel'schen Integralfunktionen
syms s;
C_s = int(cos((pi/2) * s^2), s);
S_s = int(sin((pi/2) * s^2), s);

% Parametrische Darstellung der Klothoide
x = A * sqrt(pi) * C_s;
y = A * sqrt(pi) * S_s;

% Parametrisierung für T
t_values = linspace(0, T, 1000);
x_values = double(subs(x, s, t_values));
y_values = double(subs(y, s, t_values));

% Plotten der Klothoide
figure;
plot(x_values, y_values, '-r');
grid on;
xlabel('x');
ylabel('y');