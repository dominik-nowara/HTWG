clear;

% Parameter
A = 1.2;
T = 1.5;
b = [-1; 0.9];

% Definition der Fresnel'schen Integralfunktionen
syms s;
C_s = int(cos((pi/2) * s^2), s);
S_s = int(sin((pi/2) * s^2), s);

% Parametrische Darstellung der Klothoide

x = A * (A * sqrt(pi) * C_s) + b(1);
y = A * (A * sqrt(pi) * S_s) + b(2);

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

disp('x_values:');
disp(x_values);

disp('y_values:');
bdisp(y_values);